# Aplicación de Monitoreo de Criptomonedas

Este proyecto contiene un ejemplo sencillo de una aplicación de escritorio escrita en Python.
Permite consultar precios de criptomonedas en tiempo real, visualizar su histórico en gráficos
y gestionar un portafolio simulado.

## Requisitos

- Python 3 (incluye Tkinter y las bibliotecas usadas)

No es necesario instalar paquetes adicionales.

## Uso

Ejecuta la aplicación principal:

```bash
python3 crypto_app.py
```

Se abrirá una ventana con una lista de criptomonedas populares (BTC, ETH, ADA,
XRP, DOGE, SOL, LTC y DOT).
Podrás consultar el precio actual, elegir el periodo histórico (1, 7, 30 o 90 días) y
ver gráficos suavizados de su evolución. También puedes añadir criptomonedas a un
portafolio simulado. El portafolio se puede exportar a `reporte.csv` o `reporte.txt`.
