import requests

API_BASE = 'https://api.coingecko.com/api/v3'

def get_price(crypto_id='bitcoin', vs_currency='usd'):
    """Return current price for a single crypto_id in vs_currency."""
    url = f"{API_BASE}/simple/price"
    params = {'ids': crypto_id, 'vs_currencies': vs_currency}
    try:
        response = requests.get(url, params=params, timeout=10)
        response.raise_for_status()
        data = response.json()
        return data[crypto_id][vs_currency]
    except Exception as e:
        print(f"Error fetching price: {e}")
        return None

def get_history(crypto_id='bitcoin', days=30, vs_currency='usd'):
    """Return historical price data list of [timestamp, price]."""
    url = f"{API_BASE}/coins/{crypto_id}/market_chart"
    params = {'vs_currency': vs_currency, 'days': days}
    try:
        response = requests.get(url, params=params, timeout=10)
        response.raise_for_status()
        return response.json().get('prices', [])
    except Exception as e:
        print(f"Error fetching history: {e}")
        return []
