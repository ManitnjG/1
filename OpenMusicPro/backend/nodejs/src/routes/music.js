import { Router } from 'express';

export const musicRouter = Router();
musicRouter.get('/trending', (_, res) => {
  res.json([{ id: '1', title: 'Open Track', artist: 'Community Artist', source: 'Jamendo' }]);
});
