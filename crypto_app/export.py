import pandas as pd
from reportlab.lib.pagesizes import letter
from reportlab.pdfgen import canvas


def export_excel(data, path='report.xlsx'):
    """Export portfolio data to Excel."""
    df = pd.DataFrame(list(data.items()), columns=['Crypto', 'Amount'])
    df.to_excel(path, index=False)
    print(f'Exported Excel to {path}')


def export_pdf(data, path='report.pdf'):
    """Export portfolio data to PDF."""
    c = canvas.Canvas(path, pagesize=letter)
    width, height = letter
    y = height - 40
    c.drawString(40, y, 'Crypto Portfolio Report')
    y -= 20
    for crypto, amount in data.items():
        c.drawString(40, y, f'{crypto}: {amount}')
        y -= 15
    c.save()
    print(f'Exported PDF to {path}')
