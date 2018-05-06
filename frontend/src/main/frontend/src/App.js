import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import './App.css';

import { AnswerSearchDashboard } from './components';

class App extends Component {
  render() {
    return (
      <MuiThemeProvider>
        <div className="App">
          <header>
            <h1 className="App-title">Search for your answer</h1>
          </header>
          <div align="center">
            <AnswerSearchDashboard />
          </div>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;
