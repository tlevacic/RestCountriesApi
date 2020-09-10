const defaultTheme = require('tailwindcss/defaultTheme')
module.exports = {
  future: {
    removeDeprecatedGapUtilities: true,
  },
  theme: {
    extend:{
      colors:{
        'darkBlueDarkMode':'hsl(209, 23%, 22%)',
        'veryDarkBlueDarkMode':'hsl(207, 26%, 17%)',
        'veryDarkBlueLightMode': 'hsl(200, 15%, 8%)',
        'darkGrayLightMode':'hsl(0, 0%, 52%)',
        'veryLightGrayLightMode':'hsl(0, 0%, 98%)',
        'white':'hsl(0, 0%, 100%)'
      },
      fontFamily: {
        'Nunito': ['Nunito', 'sans-serif']
      }
    }
  }
};