import React, {Component} from "react";
import axios from 'axios';

class MontyHall extends Component {
  
  constructor(props) {
    super(props);
    this.state = { 
    	status: 'Run simulation to get a result!',
	    gameSettings: {
	    	switch: false,
	    	numberOfGames: 100000
    	}
    	
    };
    
    this.handleInputChange = this.handleInputChange.bind(this);
    this.getPercentageWins = this.getPercentageWins.bind(this);
    this.handleRunSimulation = this.handleRunSimulation.bind(this);
    this.validateInput = this.validateInput.bind(this);
  }
  
  validateInput(name, value) {
  	var errors= [];
  	var regexNumber = /^[0-9]+$/g;
  	if (name === 'numberOfGames') {
  	const matchResult= value.match(regexNumber);
  		if (matchResult === null || matchResult.length !== 1) {
			errors.push('numberOfGames needs to be a number!');
  		}
  	}
  	return errors;
  }
  
  handleInputChange(event) {
    const target = event.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;
  	const errors= this.validateInput(name, value);
  	if(errors.length === 0) {
		var gameSettings = this.state.gameSettings;
		gameSettings[name] = value;
	    this.setState({ gameSettings, status: 'Run simulation to get a result!' });
    } else {
    	this.setState({status: errors.join()});
    }
  }
  
  handleRunSimulation(event) {
        event.preventDefault();
        this.getPercentageWins();
        
  }

    render() {
      return (
      	 <div>
	      	<form onSubmit={this.handleRunSimulation}>
	      	  <label>
			    Number of simulations:
			    <input 
			    	type="text" 
			    	name="numberOfGames" 
			    	onChange={this.handleInputChange} />
			  </label>
			  <label>
			    switch door:
	          <input
	            name="switchDoor"
	            type="checkbox"
	            checked={this.state.switch}
	            onChange={this.handleInputChange} />
			  </label>
	      		<input type="submit" value="Run" />
	      	</form>
	        <p>{this.state.status}</p>
        </div>
      );
    }

    getPercentageWins = () => {
      this.setState({ status: 'Waiting for result..' });
      axios.post(`/rest/percentageWins`,this.state.gameSettings)
        .then(res => {
          if (res.status === 200) {
            this.setState({ status: 'Win percentage is '+ res.data });
          } else {
            this.setState({ status: 'Failed to retrive test results!' });            
          }
        })
        .catch(res => {
          console.log(res);
          this.setState({ status: 'Failed to retrive test results!' });            
        });    
    }    
  }
  
  export default MontyHall;