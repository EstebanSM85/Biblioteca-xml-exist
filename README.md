📚 Sistema de Gestión de Biblioteca con eXist-db
Sistema de gestión de documentos de biblioteca desarrollado en Java utilizando eXist-db como base de datos XML nativa y XQuery/XQuery Update para la manipulación de datos. Permite almacenar, consultar, insertar y eliminar documentos directamente dentro de un archivo XML centralizado.

🚀 Características

           ✅ Gestión de documentos: Añadir, eliminar, listar y visualizar documentos
           
           ✅ Consultas XQuery: Recuperación eficiente de información
           
           ✅ Inserción dinámica: Los documentos se insertan dentro de documentos.xml
           
           ✅ Eliminación segura: Eliminación mediante XQuery Update
           
           ✅ Validación XML: Validación opcional del XML local
           
           ✅ Interfaz por consola: Menú interactivo sencillo y claro
           
           ✅ Integración completa con eXist-db: Conexión, colecciones y operaciones XML




🛠️ Tecnologías Utilizadas
         
           Java 23: Lenguaje de programación principal
           
           Maven: Gestión de dependencias y construcción del proyecto
           
           eXist-db 6: Base de datos XML nativa
           
           API XML:DB: Comunicación con eXist-db
           
           Eclipse IDE: Entorno de desarrollo


📋 Requisitos Previos
Antes de ejecutar el proyecto, asegúrate de tener instalado:
- JDK 23 o superior
- Maven 3.6+
- eXist-db 6 (corriendo en http://localhost:8080)
- Eclipse IDE (opcional)
Configuración de eXist-db
- Descargar e instalar eXist-db desde https://exist-db.org
- Iniciar eXist-db
- Acceder a la interfaz web en http://localhost:8080/exist
- Credenciales por defecto:
- Usuario: admin
- Contraseña: admin

📥 Instalación
1. Clonar el repositorio
git clone https://github.com/tu-usuario/biblioteca-existdb.git
cd biblioteca-existdb


2. Compilar el proyecto
mvn clean install


3. Importar en Eclipse (opcional)
File → Import → Maven → Existing Maven Projects → Seleccionar carpeta → Finish

▶️ Ejecución
Desde Eclipse
Abrir el proyecto
Click derecho en Main.java → Run As → Java Application
Desde línea de comandos
mvn exec:java -Dexec.mainClass="com.biblioteca.main.Main"



📖 Uso del Sistema
Al ejecutar la aplicación, se muestra un menú interactivo:
========================================
           MENÚ BIBLIOTECA
========================================
1. Leer documentos
2. Añadir documento
3. Eliminar documento
4. Subir XML local a eXist-db
5. Consultas XQuery
6. Salir
========================================


Ejemplos de uso
📄 Leer documentos

           Opción: 1
           1 - El Quijote
           2 - Harry Potter y la cámara secreta
           3 - 1984
           ...


➕ Añadir documento

           Opción: 2
           ID: 6
           Título: El último secreto
           Autor: Dan Brown
           Editorial: Planeta
           Fecha publicación: 2025-09-10
           Género: Ficción
           Descripción: ...
           ✓ Documento insertado correctamente


❌ Eliminar documento

           Opción: 3
           ID del documento a eliminar: 6
           ✓ Documento eliminado correctamente


🔍 Consultas XQuery
Incluye:
- Títulos ordenados
- Buscar por género
- Publicados después de 1950
- Buscar palabra clave
- Mostrar documento por ID
- Contar documentos

📕 Estructura del Proyecto
biblioteca-existdb/

<img width="462" height="335" alt="image" src="https://github.com/user-attachments/assets/ca3fda3e-c7ae-4056-bb95-17cbc05b1973" />




📦 Estructura de Datos XML
Los documentos se almacenan dentro de documentos.xml:
<img width="575" height="260" alt="image" src="https://github.com/user-attachments/assets/0e6c31a8-7920-4501-a8f2-6a0303535cda" />



🔧 Configuración
Cambiar la URL de eXist-db
Editar ConexionExistDB.java línea 20:

           String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";


Cambiar credenciales
Editar ConexionExistDB.java líneas 21–22:

           String USUARIO = "admin";
           String PASSWORD = "admin";


Cambiar colección base
Editar Main.java o ConexionExistDB.java:

           private static final String COLECCION = "/db/biblioteca/documentos";



🧪 Pruebas
Probar conexión con eXist-db

           curl http://localhost:8080/exist/


Debe devolver la página de inicio de eXist-db.
Verificar documentos almacenados
- Acceder a http://localhost:8080/exist
- Ir a "Browse Collections"
- Navegar a /db/biblioteca/documentos
- Abrir documentos.xml

📚 Dependencias
El proyecto utiliza las siguientes dependencias (gestionadas por Maven):
<img width="396" height="293" alt="image" src="https://github.com/user-attachments/assets/c54a06dc-b69a-4079-89ab-f35bbedb936e" />




🐛 Solución de Problemas
Error de conexión con eXist-db
Problema: Connection refused o no se pudo conectar
Solución:
- Verificar que eXist-db está corriendo
- Comprobar el puerto en la configuración
- Verificar credenciales
Error de validación XML
Problema: El XML no es válido
Solución:
- Verificar que todos los campos requeridos están presentes
- Asegurar que la fecha tiene formato correcto
- Validar contra el esquema XSD
Error al listar documentos
Problema: No se muestran documentos
Solución:
- Verificar que la colección /db/biblioteca/documentos existe
- Comprobar permisos de lectura en eXist-db
- Asegurar que documentos.xml contiene datos válidos

🤝 Contribuciones
Las contribuciones son bienvenidas. Por favor:
- Haz fork del proyecto
- Crea una rama para tu funcionalidad
- Commit de tus cambios
- Push a tu rama
- Abre un Pull Request

📄 Licencia
Este proyecto es de código abierto y está disponible bajo la Licencia MIT.

👨‍💻 Autor
Esteban Sánchez Martínez
Proyecto de Gestión de Biblioteca con eXist-db
