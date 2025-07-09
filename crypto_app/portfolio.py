import json
from pathlib import Path
from typing import Dict

DATA_FILE = Path('portfolio.json')

class Portfolio:
    def __init__(self, path: Path = DATA_FILE):
        self.path = path
        self.data: Dict[str, float] = {}
        self.load()

    def load(self):
        if self.path.exists():
            with open(self.path, 'r') as f:
                try:
                    self.data = json.load(f)
                except json.JSONDecodeError:
                    self.data = {}
        else:
            self.data = {}

    def save(self):
        with open(self.path, 'w') as f:
            json.dump(self.data, f, indent=2)

    def add(self, crypto_id: str, amount: float):
        self.data[crypto_id] = self.data.get(crypto_id, 0) + amount
        self.save()

    def remove(self, crypto_id: str, amount: float):
        if crypto_id in self.data:
            self.data[crypto_id] -= amount
            if self.data[crypto_id] <= 0:
                del self.data[crypto_id]
            self.save()

    def holdings(self) -> Dict[str, float]:
        return dict(self.data)
