import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import './App.css';
/*import { AnswerSearchDashboard } from './components';*/

class App extends Component {
    render() {
        return (
            <MuiThemeProvider>
                <div>Hello World</div>

            </MuiThemeProvider>
        );
    }
}
/*<AnswerSearchDashboard/>*/
export default App;
