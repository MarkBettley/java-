// ============================================
// ACTIVIDAD: BASE DE DATOS DOCUMENTAL - MONGODB
// ============================================

// Crear/seleccionar base de datos
use tienda_mongodb

// ============================================
// CREAR COLECCIONES
// ============================================

db.createCollection("usuarios")
db.createCollection("productos")

// ============================================
// INSERT
// ============================================

db.usuarios.insert([
    {
        nombre: "Marco Hernandez",
        correo: "marco@email.com",
        ciudad: "Pachuca"
    },
    {
        nombre: "Ana Lopez",
        correo: "ana@email.com",
        ciudad: "Pachuca"
    },
    {
        nombre: "Carlos Ramirez",
        correo: "carlos@email.com",
        ciudad: "Ciudad de Mexico"
    }
])

db.productos.insert([
    {
        nombre: "Laptop",
        precio: 15000,
        stock: 10
    },
    {
        nombre: "Teclado",
        precio: 900,
        stock: 25
    },
    {
        nombre: "Mouse",
        precio: 450,
        stock: 40
    }
])

// ============================================
// FIND
// ============================================

db.usuarios.find()

db.productos.find()

db.usuarios.find({ ciudad: "Pachuca" })

db.productos.find({ precio: { $gt: 500 } })

// ============================================
// REMOVE
// ============================================

db.productos.remove({ nombre: "Mouse" })

// Comprobar que Mouse fue eliminado
db.productos.find()
