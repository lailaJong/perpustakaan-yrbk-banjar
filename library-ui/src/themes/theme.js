import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    primary: {
      main: '#94806e', // Dark brown color
    },
    secondary: {
      main: '#ffbd59', // Yellow color
    },
    tertiary: {
      main: '#F44336', // Red
    }
  },
  typography: {
    fontFamily: 'Rubik , sans-serif', // Set default font
  },
  header: {
    font: 'Kanit , sans-serif',
    backgroundColor: '#ededed'
  }
});

export default theme;