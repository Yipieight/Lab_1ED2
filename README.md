
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
   git clone https://github.com/Yipieight/Lab_1ED2.git
   cd Lab_1ED2
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
O, en todo caso puede darle al icono de RUN en el IDE que se encuentre.

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

## Recomendaciones para optimizar el sistema

Durante la implementación del sistema de inventarios de la librería, se identificaron varias oportunidades de mejora para optimizar el rendimiento y la eficiencia:

- **Optimización de la búsqueda**: Se recomienda implementar **índices secundarios** basados en nombres de libros. Esto mejoraría el rendimiento de las búsquedas que no se basan en el ISBN, ya que las búsquedas por nombre podrían ser más frecuentes.

- **Uso de **caching** en búsquedas frecuentes**: Para reducir el tiempo de procesamiento de consultas repetitivas, es recomendable almacenar en caché los resultados de las búsquedas más comunes, evitando la reconstrucción del árbol para cada consulta recurrente.

- **Paralelización de operaciones de actualización**: Se podrían implementar estrategias de **paralelización** o concurrencia controlada para manejar múltiples operaciones de actualización simultáneamente. Esto ayudaría a mejorar la eficiencia durante los picos de ventas y grandes cargas de trabajo.

- **Uso de algoritmos de balanceo dinámico**: A medida que el inventario crece, es importante asegurar que el árbol permanezca balanceado. Implementar algoritmos de **balanceo dinámico** podría ser útil para mantener una eficiencia constante en las búsquedas, incluso con grandes volúmenes de datos.

- **Preprocesamiento de archivos de entrada**: Para manejar de manera más eficiente grandes volúmenes de datos, sería útil realizar un **preprocesamiento** de los archivos CSV de entrada. Esto incluye validar y limpiar los datos antes de su inserción en el sistema, lo cual reduce errores y mejora la velocidad de inserción.

## Conclusiones

Tras la implementación del sistema de inventarios utilizando árboles B, B* y B+, se concluye lo siguiente:

- **Eficiencia en la gestión de inventarios**: La estructura de datos seleccionada ha demostrado ser eficaz para manejar grandes volúmenes de información. Las operaciones de búsqueda, inserción, actualización y eliminación se realizan de manera eficiente, lo cual mejora la experiencia del usuario y optimiza el rendimiento del sistema.

- **Mejora en los tiempos de respuesta**: Gracias a la utilización de árboles B, el sistema mantiene los tiempos de respuesta constantes, incluso con un inventario que crece constantemente. Esta característica es especialmente útil durante picos de ventas, cuando la demanda de consultas es más alta.

- **Facilidad de mantenimiento y escalabilidad**: La estructura de datos implementada facilita la expansión del sistema. La inclusión de nuevas funcionalidades, como el procesamiento de diferentes tipos de artículos, será sencilla gracias a la modularidad de la solución.

- **Posibles mejoras**: Aunque el sistema actual cumple con los requisitos planteados, se han identificado varias áreas de mejora que podrían implementarse en futuras versiones, como la paralelización de operaciones, el caching de búsquedas frecuentes y la optimización de índices secundarios.

En resumen, el sistema de inventarios propuesto satisface las necesidades actuales de la librería "Libros y Más", proporcionando una plataforma robusta, escalable y eficiente para la gestión de inventarios.




## Gracias!
