import tkinter as tk

class Application(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("App de Escritorio")
        self.geometry("300x200")
        label = tk.Label(self, text="Hola, Mundo!")
        label.pack(pady=20)

if __name__ == "__main__":
    app = Application()
    app.mainloop()
