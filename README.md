# Aplicación de Monitoreo de Criptomonedas

Este proyecto contiene un ejemplo sencillo de una aplicación de escritorio escrita en Python.
Permite consultar precios de criptomonedas en tiempo real, visualizar su histórico en gráficos
y gestionar un portafolio simulado.

## Requisitos

- Python 3
- Las siguientes dependencias instaladas con `pip`:
  - requests
  - matplotlib
  - pandas
  - openpyxl
  - reportlab
  - PyQt5 (para la interfaz gráfica se usa Tkinter por defecto, pero PyQt5 es opcional)

Instálalas con:

```bash
pip install requests matplotlib pandas openpyxl reportlab PyQt5
```

## Uso

Ejecuta la aplicación principal:

```bash
python3 crypto_app.py
```

Se abrirá una ventana con la lista de criptomonedas disponibles.
Podrás consultar el precio actual, ver un gráfico histórico de 30 días
y añadir criptomonedas a un portafolio simulado. También es posible exportar
el portafolio a un archivo Excel o PDF.
