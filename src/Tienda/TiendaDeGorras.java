package Tienda;
import java.util.Scanner;

public class TiendaDeGorras {

    static String[] usuarios = new String[10];
    static String[] contrasenas = new String[10];
    static String[] gorras = new String[10];
    static int[] inventario = new int[10];
    static double[] precios = new double[10];
    static final double IVA = 0.04;

    static int totalGorras = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sesionIniciada = false;
        int opcion;

        do {
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("Elige una opción:");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 1) {
                registrarse(scanner);
            } else if (opcion == 2) {
                sesionIniciada = iniciarSesion(scanner);
            }
        } while (!sesionIniciada);

        do {
            System.out.println("----- Menú de opciones -----");
            System.out.println("1. Agregar lote de gorras");
            System.out.println("2. Consultar inventario");
            System.out.println("3. Vender gorra");
            System.out.println("4. Calcular IVA de una gorra");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarLote(scanner);
                    break;
                case 2:
                    consultarInventario();
                    break;
                case 3:
                    venderGorra(scanner);
                    break;
                case 4:
                    calcularIVA(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    public static void registrarse(Scanner scanner) {
        System.out.print("Introduce tu nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contrasena = scanner.nextLine();

        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = usuario;
                contrasenas[i] = contrasena;
                System.out.println("Usuario registrado exitosamente.");
                break;
            }
        }
    }

    public static boolean iniciarSesion(Scanner scanner) {
        System.out.print("Introduce tu nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contrasena = scanner.nextLine();

        for (int i = 0; i < usuarios.length; i++) {
            if (usuario.equals(usuarios[i]) && contrasena.equals(contrasenas[i])) {
                System.out.println("Inicio de sesión exitoso.");
                return true;
            }
        }
        System.out.println("Usuario o contraseña incorrectos.");
        return false;
    }

    public static void agregarLote(Scanner scanner) {
        if (totalGorras >= gorras.length) {
            System.out.println("No se pueden agregar más gorras, inventario lleno.");
            return;
        }
        System.out.print("Nombre de la gorra: ");
        String nombreGorra = scanner.next();
        System.out.print("Cantidad de gorras: ");
        int cantidad = scanner.nextInt();
        System.out.print("Precio de la gorra: ");
        double precio = scanner.nextDouble();

        gorras[totalGorras] = nombreGorra;
        inventario[totalGorras] = cantidad;
        precios[totalGorras] = precio;
        totalGorras++;

        System.out.println("Lote de gorras agregado.");
    }

    public static void consultarInventario() {
        System.out.println("Inventario de gorras:");
        for (int i = 0; i < totalGorras; i++) {
            System.out.println(gorras[i] + " : " + inventario[i] + " en stock, Precio Unidad: $" + precios[i]);
        }
    }

    public static void venderGorra(Scanner scanner) {
        System.out.print("Introduce el nombre de la gorra a vender: ");
        String nombreGorra = scanner.next();
        boolean encontrado = false;

        for (int i = 0; i < totalGorras; i++) {
            if (gorras[i].equals(nombreGorra)) {
                if (inventario[i] > 0) {
                    inventario[i]--;
                    System.out.println("Gorra vendida. Quedan " + inventario[i] + " en inventario.");
                } else {
                    System.out.println("No hay stock de esta gorra.");
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Gorra no encontrada en inventario.");
        }
    }

    public static void calcularIVA(Scanner scanner) {
        System.out.print("Introduce el nombre de la gorra para calcular el IVA: ");
        String nombreGorra = scanner.next();
        boolean encontrado = false;

        for (int i = 0; i < totalGorras; i++) {
            if (gorras[i].equals(nombreGorra)) {
                double precioConIVA = precios[i] * (1 + IVA);
                System.out.println("El precio con IVA de la gorra " + nombreGorra + " es: $" + precioConIVA);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Gorra no encontrada en inventario.");
        }
    }
}
