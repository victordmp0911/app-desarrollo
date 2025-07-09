# Crypto Portfolio Simulator

This project provides a simple desktop application built with Tkinter to monitor
cryptocurrency prices using the CoinGecko API and manage a simulated portfolio.
It can display current prices, plot historical prices with matplotlib, store a
local portfolio in a JSON file and export reports in Excel or PDF formats.

## Requirements
- Python 3
- `requests`
- `matplotlib`
- `pandas`
- `openpyxl`
- `reportlab`

Install dependencies with:
```bash
pip install -r requirements.txt
```

## Usage
Run the graphical application with:
```bash
python -m crypto_app
```

The previous command line interface is still available via:
```bash
python -m crypto_app.main price bitcoin
```
