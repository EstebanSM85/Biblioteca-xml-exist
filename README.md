Por supuesto, Esteban.
Aquí tienes el README completo, limpio, profesional y ahora con iconos, manteniendo un estilo claro y bien estructurado para GitHub.
Listo para copiar y pegar.

📚 Sistema de Gestión de Biblioteca con eXist-db
Aplicación desarrollada en Java que gestiona documentos almacenados en una base de datos nativa XML utilizando eXist-db. Permite realizar operaciones de lectura, inserción, eliminación, validación y consultas XQuery sobre un archivo XML centralizado.
Incluye tanto un menú por consola como una interfaz gráfica con botones para crear documentos y salir de la aplicación.

🚀 Características
- ✔️ Gestión de documentos: añadir, eliminar, listar y visualizar
- ✔️ Consultas XQuery para recuperar información
- ✔️ Inserción dinámica dentro de documentos.xml
- ✔️ Eliminación mediante XQuery Update
- ✔️ Validación XML mediante XSD
- ✔️ Menú por consola
- ✔️ Interfaz gráfica con botones “Crear” y “Salir”
- ✔️ Integración completa con eXist-db mediante API XML:DB

🛠️ Tecnologías utilizadas
- ☕ Java 23
- 📦 Maven
- 🗂️ eXist-db 6
- 🔧 API XML:DB
- 📄 XML, XSD
- 🖥️ Eclipse IDE (opcional)

📋 Requisitos previos
Antes de ejecutar el proyecto, asegúrate de tener instalado:
- 🔹 JDK 23 o superior
- 🔹 Maven 3.6+
- 🔹 eXist-db 6 (http://localhost:8080)
- 🔹 Eclipse IDE (opcional)
⚙️ Configuración de eXist-db
- Descargar desde https://exist-db.org
- Instalar y ejecutar
- Acceder a http://localhost:8080/exist
- Credenciales por defecto:
- Usuario: admin
- Contraseña: admin

📥 Instalación
Clonar el repositorio:

           git clone https://github.com/tu-usuario/biblioteca-existdb.git
           cd biblioteca-existdb


Compilar:

           mvn clean install


Importar en Eclipse (opcional):
File → Import → Maven → Existing Maven Projects

▶️ Ejecución
Desde Eclipse
Run As → Java Application sobre Main.java
Desde terminal

           mvn exec:java -Dexec.mainClass="com.biblioteca.main.Main"



📖 Uso del sistema
Al iniciar la aplicación se muestra un menú interactivo:

           MENÚ BIBLIOTECA
           1. Leer documentos
           2. Añadir documento
           3. Eliminar documento
           4. Subir XML local
           5. Consultas XQuery
           6. Salir


Ejemplos
📄 Leer documentos
           
           1 - El Quijote
           2 - Harry Potter y la cámara secreta
           3 - 1984


➕ Añadir documento

           ID: 6
           Título: El último secreto
           Autor: Dan Brown
           ...
           Documento insertado correctamente


❌ Eliminar documento

           ID del documento: 6
           Documento eliminado correctamente


🔍 Consultas XQuery
- Títulos ordenados
- Buscar por género
- Publicados después de 1950
- Buscar palabra clave
- Mostrar documento por ID
- Contar documentos

🖥️ Interfaz gráfica
La aplicación incluye una interfaz gráfica con los botones:
- Crear → Inserta un nuevo documento en eXist-db
- Salir → Cierra la aplicación
Además, se han añadido ventanas para:
- Leer documentos
- Eliminar documentos
- Subir XML local
- Ejecutar consultas XQuery

📁 Estructura del proyecto

           biblioteca-existdb/
           src/
             main/
               java/
                 com/biblioteca/
                   model/
                   db/
                   main/
                   ui/
               resources/
                 schemas/
           pom.xml
README.md



📦 Estructura de datos XML
Los documentos se almacenan en:

           /db/biblioteca/documentos/documentos.xml


Cada documento contiene:
- id
- titulo
- autor
- editorial
- fecha_publicacion
- genero
- descripcion
- contenido

🔧 Configuración del proyecto
Modificar la URL en ConexionExistDB.java:

           String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";


Credenciales:

           String USUARIO = "admin";
           String PASSWORD = "admin";


Colección base:

           private static final String COLECCION = "/db/biblioteca/documentos";



🧪 Pruebas realizadas
- Conexión con eXist-db
- Lectura de documentos
- Inserción mediante XQuery Update
- Eliminación por ID
- Subida de XML local
- Consultas XQuery
- Validación mediante XSD

🐛 Solución de problemas
Connection refused
- Verificar que eXist-db está en ejecución
- Revisar puerto y credenciales
XML no válido
- Revisar campos obligatorios
- Validar contra XSD
No se muestran documentos
- Comprobar colección
- Revisar permisos
- Verificar contenido de documentos.xml

🤝 Contribuciones
- Fork del repositorio
- Crear rama
- Commit
- Push
- Pull Request

📄 Licencia
Proyecto disponible bajo licencia MIT.

👨‍💻 Autor
Esteban Sánchez Martínez
Proyecto de Gestión de Biblioteca con eXist-db
