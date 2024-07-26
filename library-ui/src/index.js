import React from 'react';
import { createRoot } from 'react-dom/client';
import { Provider } from 'react-redux';
import { ThemeProvider } from '@mui/material/styles';
import store from './authentication/store';
import App from './App';
import theme from './themes/theme';

createRoot(document.getElementById("root")).render(
  <ThemeProvider theme={theme} >
    <Provider store={store}>
      <App />
    </Provider>
  </ThemeProvider>
);