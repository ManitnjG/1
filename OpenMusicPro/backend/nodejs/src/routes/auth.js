import { Router } from 'express';
import jwt from 'jsonwebtoken';

export const authRouter = Router();
authRouter.post('/login', (req, res) => {
  const token = jwt.sign({ sub: req.body.email ?? 'guest' }, process.env.JWT_SECRET || 'dev_secret', { expiresIn: '7d' });
  res.json({ token });
});
