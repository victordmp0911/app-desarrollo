import matplotlib.pyplot as plt

def plot_history(history, crypto_id='bitcoin'):
    """Plot price history returned from get_history."""
    if not history:
        print('No history data to plot')
        return
    timestamps, prices = zip(*history)
    plt.figure(figsize=(8, 4))
    plt.plot(timestamps, prices, label=crypto_id)
    plt.xlabel('Timestamp')
    plt.ylabel('Price (USD)')
    plt.title(f'{crypto_id} price history')
    plt.legend()
    plt.tight_layout()
    plt.show()
