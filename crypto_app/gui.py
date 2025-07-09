import tkinter as tk
from tkinter import ttk, messagebox

from .api import get_price, get_history
from .portfolio import Portfolio
from .plot import plot_history
from .export import export_excel, export_pdf


class CryptoApp(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Crypto Portfolio Simulator")
        self.portfolio = Portfolio()
        self._build_ui()

    def _build_ui(self):
        frame = ttk.Frame(self)
        frame.pack(padx=10, pady=10)

        ttk.Label(frame, text="Crypto ID:").grid(row=0, column=0, sticky="w")
        self.crypto_entry = ttk.Entry(frame)
        self.crypto_entry.grid(row=0, column=1, sticky="ew")
        ttk.Button(frame, text="Get Price", command=self.show_price).grid(row=0, column=2)
        self.price_var = tk.StringVar()
        ttk.Label(frame, textvariable=self.price_var).grid(row=0, column=3, padx=5)

        ttk.Label(frame, text="Amount:").grid(row=1, column=0, sticky="w")
        self.amount_entry = ttk.Entry(frame)
        self.amount_entry.grid(row=1, column=1, sticky="ew")
        ttk.Button(frame, text="Add", command=self.add).grid(row=1, column=2)
        ttk.Button(frame, text="Remove", command=self.remove).grid(row=1, column=3)
        ttk.Button(frame, text="Plot History", command=self.plot).grid(row=1, column=4)

        frame.columnconfigure(1, weight=1)

        self.holdings_text = tk.Text(self, width=40, height=10)
        self.holdings_text.pack(padx=10, pady=10)

        btn_frame = ttk.Frame(self)
        btn_frame.pack(padx=10, pady=5)
        ttk.Button(btn_frame, text="Refresh Portfolio", command=self.refresh_portfolio).grid(row=0, column=0, padx=5)
        ttk.Button(btn_frame, text="Export Excel", command=self.export_excel).grid(row=0, column=1, padx=5)
        ttk.Button(btn_frame, text="Export PDF", command=self.export_pdf).grid(row=0, column=2, padx=5)

        self.refresh_portfolio()

    def show_price(self):
        cid = self.crypto_entry.get().strip()
        if not cid:
            messagebox.showinfo("Input required", "Enter a crypto ID")
            return
        price = get_price(cid)
        if price is None:
            self.price_var.set("N/A")
        else:
            self.price_var.set(f"${price}")

    def plot(self):
        cid = self.crypto_entry.get().strip()
        if not cid:
            messagebox.showinfo("Input required", "Enter a crypto ID")
            return
        data = get_history(cid, days=30)
        plot_history(data, cid)

    def add(self):
        cid = self.crypto_entry.get().strip()
        try:
            amount = float(self.amount_entry.get())
        except ValueError:
            messagebox.showinfo("Invalid amount", "Please enter a numeric amount")
            return
        if cid:
            self.portfolio.add(cid, amount)
            self.refresh_portfolio()

    def remove(self):
        cid = self.crypto_entry.get().strip()
        try:
            amount = float(self.amount_entry.get())
        except ValueError:
            messagebox.showinfo("Invalid amount", "Please enter a numeric amount")
            return
        if cid:
            self.portfolio.remove(cid, amount)
            self.refresh_portfolio()

    def refresh_portfolio(self):
        self.holdings_text.delete("1.0", tk.END)
        for cid, amount in self.portfolio.holdings().items():
            self.holdings_text.insert(tk.END, f"{cid}: {amount}\n")

    def export_excel(self):
        export_excel(self.portfolio.holdings())

    def export_pdf(self):
        export_pdf(self.portfolio.holdings())


def main():
    app = CryptoApp()
    app.mainloop()


if __name__ == "__main__":
    main()
