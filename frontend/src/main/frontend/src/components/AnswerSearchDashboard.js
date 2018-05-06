import React, { Component } from 'react';

import Paper from 'material-ui/Paper';

export default class AnswerSearchDashboard extends React.Component {
	
  constructor(props) {
    super(props);
    this.state = {
        selectedIndex: 0
	};
  }

  select = (index) => this.setState({selectedIndex: index});
  
  render() {
	return (
        <Paper zDepth={1}>
	  		<BottomNavigation selectedIndex={this.state.selectedIndex}>
				<BottomNavigationItem
					label="Recents"
					onClick={() => this.select(0)}
				/>
				<BottomNavigationItem
					label="Favorites"
					onClick={() => this.select(1)}
				/>
				<BottomNavigationItem
					label="Nearby"
					onClick={() => this.select(2)}
				/>
		  	</BottomNavigation>
	  	</Paper>
	)
  }
}