import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    primary: {
      main: '#94806e', // Dark brown color
      contrastText: 'white',
    },
    secondary: {
      main: '#ffbd59', // Yellow color
      contrastText: 'white',
    },
    tertiary: {
      main: '#F44336', // Red
      contrastText: 'white',
    },
    addItem: {
      main: '#FFA726', // Red
      light: '#f2b966',
      dark: '#FB8C00',
      contrastText: 'white',
    }
  },
  typography: {
    fontFamily: 'Rubik , sans-serif', // Set default font
  },
  header: {
    font: 'Kanit , sans-serif',
    backgroundColor: '#ededed' // Grey
  },
  banner: {
    font: 'Salsa , sans-serif',
    backgroundColor: '#785001' // Brown
  }
});

export default theme;