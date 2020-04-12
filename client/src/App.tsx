import * as React from "react";
import logo from './logo.svg';
import './App.css';

export interface AppProps {
  compiler?: string;
  framework?: string;
}

interface IState {
  data: User[];
}

interface User {
  id: number;
  firstName: string;
  lastName: string;
}

export class App extends React.Component<AppProps, IState> {

  constructor(iprops: AppProps){
    super(iprops);
    this.state = {data: []};
  }

  componentDidMount() {
    //fetch('http://jsonplaceholder.typicode.com/users')
    //fetch('https://raw.githubusercontent.com/ag-grid/ag-grid/master/packages/ag-grid-docs/src/olympicWinnersSmall.json')
    fetch('/users')
    .then(res => res.json())
    .then((data) => {
      console.log('Data Received: ', data);
      this.setState({data: data});
    })
    .catch(err => console.log("Error occurred: ", err));
  }


  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.tsx</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
            {!this.state.data ? '' :this.getTable()}
          </a>
        </header>
      </div>
    );
  }

  private getTable(){
        return (
          <table>
            <thead>
              <tr>
                <td>First Name</td>
                <td>Last Name</td>
              </tr>
            </thead>
            <tbody>
              {this.state.data.map(user => <tr key={user.id} ><td>{user.firstName}</td><td>{user.lastName}</td></tr>)} 
            </tbody>
          </table>
        );
  }  
}

export default App;
