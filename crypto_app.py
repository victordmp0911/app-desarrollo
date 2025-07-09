import json
import requests
import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime

import matplotlib.pyplot as plt
import pandas as pd
from reportlab.platypus import SimpleDocTemplate, Table

PORTFOLIO_FILE = 'portfolio.json'
CRYPTO_OPTIONS = [
    ('bitcoin', 'BTC'),
    ('ethereum', 'ETH'),
    ('cardano', 'ADA')
]


def get_price(crypto_id='bitcoin', vs_currency='usd'):
    url = f'https://api.coingecko.com/api/v3/simple/price?ids={crypto_id}&vs_currencies={vs_currency}'
    response = requests.get(url, timeout=10)
    response.raise_for_status()
    data = response.json()
    return data[crypto_id][vs_currency]


def get_history(crypto_id='bitcoin', days=30, vs_currency='usd'):
    url = f'https://api.coingecko.com/api/v3/coins/{crypto_id}/market_chart?vs_currency={vs_currency}&days={days}'
    response = requests.get(url, timeout=10)
    response.raise_for_status()
    return response.json()['prices']


class CryptoApp(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title('Monitoreo de Criptomonedas')
        self.geometry('600x400')
        self.portfolio = self.load_portfolio()
        self.create_widgets()

    def load_portfolio(self):
        try:
            with open(PORTFOLIO_FILE, 'r') as f:
                return json.load(f)
        except FileNotFoundError:
            return {}

    def save_portfolio(self):
        with open(PORTFOLIO_FILE, 'w') as f:
            json.dump(self.portfolio, f)

    def create_widgets(self):
        # Dropdown for crypto selection
        self.crypto_var = tk.StringVar(value=CRYPTO_OPTIONS[0][0])
        ttk.Label(self, text='Criptomoneda:').grid(row=0, column=0, sticky='w', padx=5, pady=5)
        options = [name for name, _ in CRYPTO_OPTIONS]
        self.crypto_menu = ttk.OptionMenu(self, self.crypto_var, options[0], *options, command=self.update_price)
        self.crypto_menu.grid(row=0, column=1, sticky='w')

        # Price label
        self.price_var = tk.StringVar(value='Precio: --')
        self.price_label = ttk.Label(self, textvariable=self.price_var)
        self.price_label.grid(row=0, column=2, padx=5)
        ttk.Button(self, text='Actualizar', command=self.update_price).grid(row=0, column=3, padx=5)
        ttk.Button(self, text='Ver gráfico', command=self.show_history).grid(row=0, column=4, padx=5)

        # Portfolio section
        ttk.Label(self, text='Portafolio').grid(row=1, column=0, pady=10, sticky='w')
        self.port_tree = ttk.Treeview(self, columns=('amount', 'value'), show='headings')
        self.port_tree.heading('amount', text='Cantidad')
        self.port_tree.heading('value', text='Valor (USD)')
        self.port_tree.grid(row=2, column=0, columnspan=5, padx=5, sticky='nsew')

        # Add crypto to portfolio
        self.amount_var = tk.StringVar()
        ttk.Entry(self, textvariable=self.amount_var).grid(row=3, column=1, padx=5)
        ttk.Button(self, text='Agregar', command=self.add_to_portfolio).grid(row=3, column=2, padx=5)
        ttk.Button(self, text='Exportar Excel', command=self.export_excel).grid(row=4, column=1, pady=10)
        ttk.Button(self, text='Exportar PDF', command=self.export_pdf).grid(row=4, column=2, pady=10)

        self.grid_rowconfigure(2, weight=1)
        self.grid_columnconfigure(4, weight=1)
        self.refresh_portfolio_view()
        self.update_price()

    def update_price(self, *args):
        crypto = self.crypto_var.get()
        try:
            price = get_price(crypto)
            self.price_var.set(f'Precio: ${price}')
        except Exception as e:
            messagebox.showerror('Error', f'No se pudo obtener el precio: {e}')

    def show_history(self):
        crypto = self.crypto_var.get()
        try:
            data = get_history(crypto, days=30)
            dates = [datetime.fromtimestamp(p[0]/1000) for p in data]
            prices = [p[1] for p in data]
            plt.figure(figsize=(8,4))
            plt.plot(dates, prices)
            plt.title(f'Historico 30 dias - {crypto}')
            plt.xlabel('Fecha')
            plt.ylabel('Precio (USD)')
            plt.tight_layout()
            plt.show()
        except Exception as e:
            messagebox.showerror('Error', f'No se pudo obtener el historico: {e}')

    def add_to_portfolio(self):
        crypto = self.crypto_var.get()
        try:
            amount = float(self.amount_var.get())
        except ValueError:
            messagebox.showerror('Error', 'Cantidad no valida')
            return
        self.portfolio[crypto] = self.portfolio.get(crypto, 0) + amount
        self.save_portfolio()
        self.refresh_portfolio_view()
        self.amount_var.set('')

    def refresh_portfolio_view(self):
        for i in self.port_tree.get_children():
            self.port_tree.delete(i)
        total_value = 0
        for crypto, amount in self.portfolio.items():
            try:
                price = get_price(crypto)
            except Exception:
                price = 0
            value = amount * price
            total_value += value
            self.port_tree.insert('', 'end', values=(f'{amount}', f'{value:.2f}'))
        self.price_label.config(text=f'Valor total: ${total_value:.2f}')

    def export_excel(self):
        if not self.portfolio:
            messagebox.showinfo('Info', 'Portafolio vacio')
            return
        data = []
        for crypto, amount in self.portfolio.items():
            price = get_price(crypto)
            data.append({'Cripto': crypto, 'Cantidad': amount, 'ValorUSD': amount * price})
        df = pd.DataFrame(data)
        df.to_excel('reporte.xlsx', index=False)
        messagebox.showinfo('Exportar', 'Reporte guardado como reporte.xlsx')

    def export_pdf(self):
        if not self.portfolio:
            messagebox.showinfo('Info', 'Portafolio vacio')
            return
        data = [['Cripto', 'Cantidad', 'ValorUSD']]
        for crypto, amount in self.portfolio.items():
            price = get_price(crypto)
            data.append([crypto, str(amount), f'{amount * price:.2f}'])
        doc = SimpleDocTemplate('reporte.pdf')
        table = Table(data)
        doc.build([table])
        messagebox.showinfo('Exportar', 'Reporte guardado como reporte.pdf')


if __name__ == '__main__':
    app = CryptoApp()
    app.mainloop()
