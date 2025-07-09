# Crypto Portfolio Simulator

This project provides a simple command line tool to monitor cryptocurrency prices
using the CoinGecko API and manage a simulated portfolio. It can display current
prices, plot historical prices with matplotlib, store a local portfolio in a JSON
file and export reports in Excel or PDF formats.

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
```
python -m crypto_app price bitcoin ethereum
python -m crypto_app history bitcoin --days 7
python -m crypto_app add bitcoin 0.5
python -m crypto_app portfolio
python -m crypto_app export --excel --pdf
```
