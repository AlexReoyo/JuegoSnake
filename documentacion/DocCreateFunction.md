# Documentación de la función create() en LibGDX

## ¿Qué es create()?

La función `create()` es lo primero que se ejecuta cuando arrancas el juego. Solo se llama una vez, al principio de todo, y es donde tienes que preparar absolutamente todo lo que vas a necesitar durante el juego.

Es como cuando enciendes la consola: antes de jugar, tiene que cargar los gráficos, la música, los controles... pues aquí es igual. Si no preparas algo en `create()`, luego el juego no puede usarlo y peta.

---

## ¿Qué tengo que hacer en create()?

Básicamente hay que inicializar tres tipos de cosas:

### 1. Los gráficos (lo que se ve en pantalla)

- **SpriteBatch**: esto es como el "pincel" del juego. Sin él no puedes dibujar nada. Cada vez que quieras mostrar una imagen, usas este objeto.
  
- **Texture**: son las imágenes del juego. Por ejemplo, la serpiente, la comida, el fondo... Hay que cargarlas todas aquí.

- **ShapeRenderer**: sirve para dibujar formas básicas como rectángulos o círculos. Útil para hacer pruebas o interfaces simples.

- **BitmapFont**: las fuentes de texto para mostrar los puntos, mensajes, etc.

### 2. El estado inicial del juego

- **Posición de la serpiente**: dónde empieza en el tablero (normalmente en el centro).

- **Dirección**: hacia dónde se mueve al principio (derecha, izquierda, arriba, abajo).

- **Velocidad**: cada cuánto se mueve la serpiente. Si pones 0.1 segundos, va rápido. Si pones 0.3, va lenta.

- **Puntuación**: la pones a 0 al empezar.

- **Comida**: generas la primera en un sitio aleatorio del tablero.

### 3. Los controles y demás

- **InputProcessor**: esto detecta cuando pulsas teclas o tocas la pantalla. Sin esto, los controles no funcionan.

- **AssetManager**: opcional, pero útil si tienes muchos archivos. Organiza la carga de imágenes y sonidos.

---

## Ejemplo de create() para nuestro Snake

Imagina que el juego necesita:
- Una serpiente que empieza con 3 trozos en el medio del tablero
- Una comida aleatoria
- Puntos en 0

Pues esto sería:

```java
@Override
public void create() {
    // Creo el objeto para dibujar
    batch = new SpriteBatch();
    
    // Cargo las imágenes
    texturaSerpiente = new Texture("snake.png");
    texturaComida = new Texture("food.png");
    texturaFondo = new Texture("fondo.png");
    
    // Creo la serpiente con 3 segmentos
    cuerpo = new Array<>();
    cuerpo.add(new Vector2(5, 5)); // Cabeza
    cuerpo.add(new Vector2(4, 5)); // Medio
    cuerpo.add(new Vector2(3, 5)); // Cola
    
    // La serpiente empieza yendo hacia la derecha
    direccion = Direccion.DERECHA;
    
    // Velocidad: se mueve cada 0.15 segundos
    velocidad = 0.15f;
    tiempo = 0f;
    
    // Puntos a cero
    puntos = 0;
    
    // Genero la primera comida
    generarComida();
    
    // Activo los controles del teclado
    Gdx.input.setInputProcessor(new ControlesTeclado());
}
```

---

## ¿Qué hace cada línea?

**batch = new SpriteBatch();**  
Crea el objeto que luego uso en `render()` para dibujar las imágenes. Si no hago esto, no puedo mostrar nada.

**texturaSerpiente = new Texture("snake.png");**  
Carga la imagen de la serpiente desde la carpeta `assets/`. Si el archivo no existe, el juego se cierra con error.

**cuerpo = new Array<>();**  
Creo una lista vacía donde voy a guardar todos los trozos de la serpiente. Cada trozo es una posición (x, y) en el tablero.

**cuerpo.add(new Vector2(5, 5));**  
Añado la cabeza de la serpiente en la posición x=5, y=5. Imagina que el tablero tiene 20 columnas y 15 filas, pues la pongo ahí.

**direccion = Direccion.DERECHA;**  
Digo que al empezar, la serpiente va hacia la derecha. Puede ser ARRIBA, ABAJO, IZQUIERDA o DERECHA.

**velocidad = 0.15f;**  
La serpiente se mueve cada 0.15 segundos (unas 6-7 veces por segundo). Si quiero que vaya más rápido, bajo este número.

**puntos = 0;**  
Al empezar el juego, tienes 0 puntos. Cada vez que comes, sumas.

**generarComida();**  
Llamo a una función que pone la comida en un sitio aleatorio (que no esté ocupado por la serpiente).

**Gdx.input.setInputProcessor(...);**  
Activo el sistema de controles. Sin esto, aunque pulses teclas, no pasa nada.

---

## Errores típicos y cómo solucionarlos

**El juego se cierra nada más abrirlo**  
→ Seguramente no has creado `batch` o alguna textura. Revisa que estén todas las líneas del `new`.

**No se ven las imágenes**  
→ La ruta está mal. Comprueba que `snake.png` esté en la carpeta `assets/` del proyecto.

**Las teclas no hacen nada**  
→ Te falta registrar el `InputProcessor`. Añade la línea `Gdx.input.setInputProcessor(...)`.

**La serpiente empieza fuera de la pantalla**  
→ Las coordenadas iniciales están mal. Si tu tablero es 20x15, los números tienen que estar entre 0-19 y 0-14.

**El juego va cada vez más lento con el tiempo**  
→ Estás cargando texturas en `render()` en lugar de en `create()`, o no las estás liberando en `dispose()`.

---

## Consejos para organizar mejor el código

En lugar de meter todo el código en `create()`, yo lo separo así:

```java
@Override
public void create() {
    inicializarGraficos();
    inicializarJuego();
    cargarImagenes();
}

private void inicializarGraficos() {
    batch = new SpriteBatch();
}

private void inicializarJuego() {
    cuerpo = new Array<>();
    cuerpo.add(new Vector2(5, 5));
    direccion = Direccion.DERECHA;
    puntos = 0;
}

private void cargarImagenes() {
    texturaSerpiente = new Texture("snake.png");
    texturaComida = new Texture("food.png");
}
```

Así es más fácil de leer y si hay un error, sé exactamente dónde está.

---

## Importante: siempre libera la memoria en dispose()

Todo lo que creas en `create()` lo tienes que destruir en `dispose()`. Si no, el ordenador se queda con la memoria ocupada aunque cierres el juego.

```java
@Override
public void dispose() {
    batch.dispose();
    texturaSerpiente.dispose();
    texturaComida.dispose();
    texturaFondo.dispose();
}
```

---

## ¿Cómo se relaciona con el resto del juego?

LibGDX llama a diferentes funciones en diferentes momentos:

1. **create()** → Se ejecuta UNA vez al abrir el juego (aquí preparas todo)
2. **resize()** → Cuando cambias el tamaño de la ventana
3. **render()** → Se ejecuta 60 veces por segundo (aquí actualizas y dibujas el juego)
4. **pause()** → Cuando minimizas el juego (en móviles)
5. **resume()** → Cuando vuelves al juego
6. **dispose()** → Se ejecuta UNA vez al cerrar (aquí liberas memoria)

**Lo importante**: `create()` prepara todo → `render()` lo usa → `dispose()` lo limpia.

---

## Checklist antes de terminar create()

Antes de dar por bueno el código, compruebo esto:

- [ ] He creado el `SpriteBatch`
- [ ] He cargado todas las imágenes (serpiente, comida, fondo)
- [ ] La serpiente tiene al menos 3 segmentos al empezar
- [ ] He puesto una dirección inicial
- [ ] He configurado la velocidad de movimiento
- [ ] He generado la primera comida
- [ ] La puntuación empieza en 0
- [ ] He registrado los controles (InputProcessor)
- [ ] He comprobado que las imágenes existen en assets/
- [ ] He planeado qué liberar en dispose()

---

## Resumen rápido

**create()** es donde preparas TODO antes de que empiece el juego:
- Cargas las imágenes
- Creas los objetos para dibujar
- Pones la serpiente en su posición inicial
- Generas la primera comida
- Activas los controles

Si algo falta aquí, el juego no funciona. Y si cargas algo aquí, acuérdate de liberarlo en `dispose()`.

---

**Tarea**: BTS-37 - Documentación de create()  
**Hecho por**: [Javier Ramos Fuster]  
**Fecha**: 22 de noviembre de 2025