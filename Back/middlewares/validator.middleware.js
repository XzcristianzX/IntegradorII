import  {validationResult} from 'express-validator';

/**
 * It takes an array of validations, runs them all, and if there are no errors, it calls the next
 * middleware. If there are errors, it returns a 422 response with the errors.
 * @returns An async function that takes in a request, response, and next.
 */
export const validate = (validations) => async (req, res, next) => {

  await Promise.all(validations.map((val) => val.run(req)));

  const errors = validationResult(req);
  if (errors.isEmpty()) {
    return next();
  }
  res.status(422).json({
    success:false,
    errors: errors.array(),
  });
};
