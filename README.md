
# Proyecto B-Tree

## Introducción

Este proyecto implementa un árbol B, una estructura de datos auto-balanceada que mantiene ordenadas las claves y permite búsquedas, inserciones, eliminaciones y actualizaciones eficientes. Los árboles B son ampliamente utilizados en bases de datos y sistemas de archivos debido a su capacidad para manejar grandes cantidades de datos de manera eficiente.

## Instalación

Para instalar y ejecutar este proyecto, sigue los siguientes pasos:

1. **Clona el repositorio:**

   ```sh
   git clone https://github.com/tu-usuario/tu-repositorio.git
   cd tu-repositorio
   ```

2. **Compila y construye el proyecto utilizando Maven:**

   Asegúrate de tener [Maven](https://maven.apache.org/install.html) instalado en tu sistema.

   ```sh
   mvn clean install
   ```

   Maven se encargará de descargar todas las dependencias necesarias especificadas en el archivo `pom.xml`.

## Uso

### Ejecución

Para ejecutar el proyecto, utiliza el siguiente comando:

```sh
mvn exec:java -Dexec.mainClass="org.example.Main"
```

### Estructura del Código

El proyecto está compuesto por los siguientes archivos principales:

- `Book.java`: Representa un libro con atributos como ISBN, nombre, autor, categoría, precio y cantidad.
- `BTree.java`: Implementa el árbol B con operaciones para insertar, eliminar, actualizar y buscar libros.
- `Main.java`: Contiene el método principal que lee datos desde un archivo CSV, realiza operaciones en el árbol B y escribe resultados en un archivo de salida.

### Funcionalidad del Árbol B

El árbol B implementa las siguientes operaciones:

- **Insertar (INSERT):** Inserta un nuevo libro en el árbol B.
- **Eliminar (DELETE):** Elimina un libro del árbol B basado en el ISBN.
- **Actualizar (PATCH):** Actualiza los atributos de un libro en el árbol B basado en el ISBN.
- **Buscar (SEARCH):** Busca un libro en el árbol B basado en el nombre.

#### Ejemplo de Operaciones

El archivo `Main.java` muestra cómo se pueden realizar estas operaciones:

- **Insertar:**
  ```java
  Book newBook = new Book("ISBN12345");
  newBook.setName("Nuevo Libro");
  newBook.setAuthor("Autor Ejemplo");
  bTree.insert(newBook);
  ```

- **Eliminar:**
  ```java
  bTree.delete("ISBN12345");
  ```

- **Actualizar:**
  ```java
  bTree.update("ISBN12345", "author", "Autor Actualizado");
  ```

- **Buscar:**
  ```java
  Book foundBook = bTree.searchByName("Nuevo Libro");
  System.out.println(foundBook);
  ```

## Licencia

Este proyecto está bajo la Licencia MIT - mira el archivo [LICENSE](LICENSE) para más detalles.

