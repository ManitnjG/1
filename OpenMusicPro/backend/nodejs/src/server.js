import express from 'express';
import cors from 'cors';
import { authRouter } from './routes/auth.js';
import { musicRouter } from './routes/music.js';

const app = express();
app.use(cors());
app.use(express.json());
app.use('/api/auth', authRouter);
app.use('/api/music', musicRouter);
app.get('/health', (_, res) => res.json({ status: 'ok' }));

app.listen(process.env.PORT || 8080, () => console.log('OpenMusic Pro backend running'));
