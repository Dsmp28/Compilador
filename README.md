# IDE para Lenguaje Propio y Análisis Léxico

Esta página web es una herramienta interactiva que funciona como un entorno de desarrollo integrado (IDE) para programar en un lenguaje propio y realizar el análisis léxico del mismo. Aquí encontrarás las instrucciones necesarias para inicializar el proyecto, así como una breve descripción de su funcionamiento.

---

## Instrucciones de Inicialización

1. **Clonar el Repositorio:**
   - Clona el repositorio en tu computador utilizando el siguiente comando:
     ```bash
     git clone https://github.com/Dsmp28/Compilador.git
     ```
   - Dirígete a la carpeta del proyecto:
     ```bash
     cd Compilador
     ```

2. **Requisitos Previos:**
   - Asegúrate de tener [Docker](https://www.docker.com/) instalado en tu computador.

3. **Configuración para Sistemas Windows:**
   - Si ejecutas el proyecto en Windows, abre una consola Bash y ubica la carpeta llamada `Compilador`.
   - Ejecuta el siguiente comando en ese archivo para convertir el script `mvnw` a un formato compatible con sistemas Linux:
     ```bash
     $ dos2unix backend/mvnw
     ```

4. **Configuración del Contenedor Docker:**
   - Abre Docker en tu sistema.
   - Con el contenedor activo, ejecuta el siguiente comando para obtener la imagen de MySQL:
     ```bash
     docker pull mysql
     ```
   - Una vez descargada la imagen, construye las imágenes y levanta el contenedor ejecutando:
     ```bash
     docker-compose up --build
     ```

---

## Uso de la Aplicación

La aplicación ofrece las siguientes funcionalidades:

- **Creación de Proyectos:**  
  Permite crear nuevos proyectos en los cuales podrás trabajar de manera independiente.

- **Estructura de Carpetas:**  
  Cada proyecto puede contener múltiples carpetas para organizar tu código de forma eficiente.

- **Gestión de Archivos de Código:**  
  Dentro de cada carpeta, puedes crear archivos de código en los que se desarrollarán tus programas. Además:
  - **Importación de Archivos:** Puedes importar archivos en formato `.txt` para cargar tanto el nombre del archivo como su contenido.
  - **Guardado y Exportación:** La aplicación permite guardar el código en desarrollo y exportarlo cuando sea necesario.

---

## Video Explicativo

A continuación se muestra un video que explica el funcionamiento del editor de código, así como el funcionamiento del analizador léxico implementado:

[Link de video explicativo](https://drive.google.com/file/d/1rttM7X2uH28qGtluYCQM2g7YKuv1_ocR/view?usp=sharing)

---

## Notas Adicionales

- Se recomienda revisar periódicamente la documentación y actualizaciones del proyecto.
- Para cualquier duda o sugerencia, no dudes en abrir una issue en el repositorio.
