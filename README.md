Perfecto, Esteban.
Aquí tienes un README completo, profesional, con iconos, emojis, secciones claras y estilo GitHub, siguiendo exactamente el formato del ejemplo que me pasaste, pero adaptado a tu proyecto de Gestión de Biblioteca con eXist-db.
Puedes copiarlo tal cual a tu repositorio.

📚 Sistema de Gestión de Biblioteca con eXist-db
Sistema de gestión de documentos de biblioteca desarrollado en Java, utilizando eXist-db como base de datos XML nativa y XQuery/XQuery Update para la manipulación de datos.
Permite almacenar, consultar, insertar y eliminar documentos directamente dentro de un archivo XML centralizado.

🚀 Características
- ✅ Gestión de documentos: Añadir, eliminar, listar y visualizar documentos
- ✅ Consultas XQuery: Recuperación eficiente de información
- ✅ Inserción dinámica: Los documentos se insertan dentro de documentos.xml
- ✅ Eliminación segura: Eliminación mediante XQuery Update
- ✅ Validación XML: Validación opcional del XML local
- ✅ Interfaz por consola: Menú interactivo sencillo y claro
- ✅ Integración completa con eXist-db: Conexión, colecciones y operaciones XML

🛠️ Tecnologías Utilizadas
- Java 17
- Maven
- eXist-db 6
- XML + XSD
- XQuery y XQuery Update
- API XML:DB
- Eclipse IDE

📋 Requisitos Previos
Antes de ejecutar el proyecto, asegúrate de tener instalado:
- JDK 17 o superior
- Maven 3.6+
- eXist-db 6 (http://localhost:8080)
- Eclipse IDE (opcional)
Configuración de eXist-db
- Descargar desde https://exist-db.org
- Iniciar eXist-db
- Acceder a: http://localhost:8080/exist
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
Click derecho en Main.java → Run As → Java Application
Desde terminal
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

🏗️ Estructura del Proyecto
biblioteca-existdb/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/biblioteca/
│   │   │       ├── model/Documento.java
│   │   │       ├── db/ConexionExistDB.java
│   │   │       ├── db/ValidadorXML.java
│   │   │       └── main/Main.java
│   │   └── resources/
│   │       └── schemas/documento.xsd
│
├── pom.xml
└── README.md



📦 Estructura de Datos XML
Los documentos se almacenan dentro de documentos.xml:
<biblioteca>
  <documento id="1">
    <titulo>Ejemplo</titulo>
    <autor>Autor</autor>
    <editorial>Editorial</editorial>
    <fecha_publicacion>2020-01-01</fecha_publicacion>
    <genero>Novela</genero>
    <descripcion>Descripción del libro</descripcion>
    <contenido>Contenido generado automáticamente</contenido>
  </documento>
</biblioteca>



🔧 Configuración
Cambiar URL de eXist-db
Editar ConexionExistDB.java
Cambiar credenciales
Modificar usuario y contraseña en la misma clase
Cambiar colección base
Ruta utilizada:
/db/biblioteca/documentos

🧪 Pruebas
Probar conexión
curl http://localhost:8080/exist


Verificar documentos
- Acceder a la interfaz web
- Browse Collections
- Navegar a /db/biblioteca/documentos
- Abrir documentos.xml

🐛 Solución de Problemas
Error al insertar documento
Causa: XML mal formado
Solución: Revisar campos vacíos o caracteres especiales
Error al eliminar documento
Causa: ID inexistente
Solución: Verificar que el documento existe
No se muestran documentos
Causa: documentos.xml vacío
Solución: Subir XML local desde el menú

👨‍💻 Autor
Esteban Sánchez Martínez
Proyecto de Gestión de Biblioteca con eXist-db
