
# Proyecto B-Tree

## Introducción

Este proyecto implementa un árbol B, una estructura de datos auto-balanceada que mantiene ordenadas las claves y permite búsquedas, inserciones, eliminaciones y actualizaciones eficientes. Los árboles B son ampliamente utilizados en bases de datos y sistemas de archivos debido a su capacidad para manejar grandes cantidades de datos de manera eficiente.

### ¿Qué es un Árbol B?

Un árbol B es una estructura de datos en forma de árbol auto-balanceado en la que los nodos pueden tener múltiples hijos. A diferencia de los árboles binarios de búsqueda, los árboles B pueden tener más de dos hijos, lo que permite reducir la altura del árbol y mejorar la eficiencia de las operaciones de búsqueda, inserción y eliminación. Un árbol B de orden t (grado mínimo) tiene las siguientes propiedades:
- Cada nodo interno puede tener al menos t hijos y como máximo 2t hijos.
- La raíz tiene al menos 2 hijos si no es una hoja.
- Todos los nodos hoja están al mismo nivel.
- Las claves en cada nodo están ordenadas de manera ascendente.

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

- **Insertar (INSERT):** Inserta un nuevo libro en el árbol B. Esta operación verifica si el nodo actual tiene espacio para la nueva clave. Si no, se divide el nodo y se inserta la clave en la posición adecuada.

- **Eliminar (DELETE):** Elimina un libro del árbol B basado en el ISBN. La operación de eliminación asegura que el árbol se mantenga balanceado y reorganiza las claves según sea necesario para mantener las propiedades del árbol B.

- **Actualizar (PATCH):** Actualiza los atributos de un libro en el árbol B basado en el ISBN. Busca el libro por su ISBN y actualiza los campos proporcionados.

- **Buscar (SEARCH):** Busca un libro en el árbol B basado en el nombre. Recorre el árbol comparando los nombres hasta encontrar el libro deseado o determinar que no está presente en el árbol.

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

### Lectura y Escritura de Archivos

El proyecto lee datos de `lab01_books.csv` y escribe los resultados en `Output_Jose_Garcia_Lab1.txt`. Asegúrate de que estos archivos estén presentes en el directorio raíz del proyecto antes de ejecutar.

## Contribuciones

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del proyecto.
2. Crea una rama para tu nueva funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza un commit de tus cambios (`git commit -am 'Añadir nueva funcionalidad'`).
4. Sube tus cambios a tu rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT - mira el archivo [LICENSE](LICENSE) para más detalles.
