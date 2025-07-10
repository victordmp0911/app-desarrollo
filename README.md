# Aplicación de Monitoreo de Criptomonedas

Este proyecto muestra una aplicación de escritorio escrita en Python que permite
consultar precios de criptomonedas, ver su histórico en gráficos y manejar un
portafolio simulado. Todo se implementa únicamente con bibliotecas incluidas en
la instalación estándar de Python, por lo que no es necesario instalar paquetes
adicionales.

## Uso

Ejecuta la aplicación principal (requiere un entorno gráfico):

```bash
python3 crypto_app.py
```

Si tu sistema no cuenta con interfaz gráfica o recibes un error relacionado con
`DISPLAY`, puedes usar el modo de consola:

```bash
python3 crypto_app.py --cli
```

En la ventana principal se listan varias criptomonedas populares (BTC, ETH, ADA,
XRP, DOGE, SOL, LTC y DOT). Se muestra su precio actual y puedes elegir ver su
histórico (1, 7, 30 o 90 días) en un gráfico con líneas suavizadas. El
portafolio simulado presenta el valor equivalente en bolívares calculado con la
tasa oficial del Banco Central de Venezuela. Antes de agregar una criptomoneda
aparecerá una previsualización del costo en USD y VES. Puedes exportar tu
portafolio a `reporte.csv` o `reporte.txt`.
