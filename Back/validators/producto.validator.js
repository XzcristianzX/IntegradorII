import { checkSchema } from 'express-validator';

// Validaciones para la ruta de creación de productos
export const createProductValidator = checkSchema({
  nombre: {
    notEmpty: true,
    isLength: {
      options: { min: 3, max: 100 },
      errorMessage: 'El nombre debe tener entre 3 y 100 caracteres'
    },
    errorMessage: 'Nombre no válido'
  },
  detalle: {
    notEmpty: true,
    matches: {
      options: /^[a-zA-Z0-9\s]+$/, // Solo permite caracteres alfanuméricos y espacios
      errorMessage: 'El detalle solo puede contener letras, números y espacios'
    },
    errorMessage: 'El detalle no puede estar vacío'
  },
  valor: {
    isFloat: {
      options: { min: 0 }, // Asegura que el valor sea un número positivo
      errorMessage: 'El valor debe ser un número positivo'
    },
    errorMessage: 'El valor debe ser un número válido'
  }
});

// Validaciones para la ruta de actualización de productos
export const updateProductValidator = checkSchema({
  nombre: {
    optional: true,
    isLength: {
      options: { min:3, max: 100 },
      errorMessage: 'El nombre debe tener como minimo 3 y máximo 100 caracteres'
    },
    errorMessage: 'Nombre no válido'
  },
  detalle: {
    optional: true,
    matches: {
      options: /^[a-zA-Z0-9\s]+$/, // Solo permite caracteres alfanuméricos y espacios
      errorMessage: 'El detalle solo puede contener letras, números y espacios'
    },
    errorMessage: 'El detalle no puede estar vacío'
  },
  valor: {
    optional: true,
    isFloat: {
      options: { min: 0 }, // Asegura que el valor sea un número positivo
      errorMessage: 'El valor debe ser un número positivo'
    },
    errorMessage: 'El valor debe ser un número válido'
  }
});

// Validaciones para la ruta de búsqueda de productos por nombre
export const getProductByNameValidator = checkSchema({
  nombre: {
    notEmpty: true,
    isLength: {
      options: { min: 3, max: 100 },
      errorMessage: 'El nombre debe tener entre 3 y 100 caracteres'
    },
    errorMessage: 'Nombre no válido'
  }
});
