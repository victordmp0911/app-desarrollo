import argparse
from .api import get_price, get_history
from .portfolio import Portfolio
from .plot import plot_history
from .export import export_excel, export_pdf


def show_prices(crypto_ids):
    for cid in crypto_ids:
        price = get_price(cid)
        if price is not None:
            print(f'{cid.upper()}: ${price}')


def show_history(crypto_id, days):
    history = get_history(crypto_id, days)
    plot_history(history, crypto_id)


def main():
    parser = argparse.ArgumentParser(description='Crypto portfolio simulator')
    subparsers = parser.add_subparsers(dest='command')

    price_p = subparsers.add_parser('price', help='Show current prices')
    price_p.add_argument('ids', nargs='+', help='Crypto IDs e.g. bitcoin')

    hist_p = subparsers.add_parser('history', help='Show price history')
    hist_p.add_argument('id', help='Crypto ID')
    hist_p.add_argument('--days', type=int, default=30, help='Days of history')

    add_p = subparsers.add_parser('add', help='Add crypto to portfolio')
    add_p.add_argument('id')
    add_p.add_argument('amount', type=float)

    remove_p = subparsers.add_parser('remove', help='Remove crypto from portfolio')
    remove_p.add_argument('id')
    remove_p.add_argument('amount', type=float)

    portfolio_p = subparsers.add_parser('portfolio', help='Show portfolio holdings')

    export_p = subparsers.add_parser('export', help='Export portfolio')
    export_p.add_argument('--excel', action='store_true')
    export_p.add_argument('--pdf', action='store_true')

    args = parser.parse_args()
    pf = Portfolio()

    if args.command == 'price':
        show_prices(args.ids)
    elif args.command == 'history':
        show_history(args.id, args.days)
    elif args.command == 'add':
        pf.add(args.id, args.amount)
    elif args.command == 'remove':
        pf.remove(args.id, args.amount)
    elif args.command == 'portfolio':
        for cid, amount in pf.holdings().items():
            print(f'{cid}: {amount}')
    elif args.command == 'export':
        if args.excel:
            export_excel(pf.holdings())
        if args.pdf:
            export_pdf(pf.holdings())
    else:
        parser.print_help()


if __name__ == '__main__':
    main()
