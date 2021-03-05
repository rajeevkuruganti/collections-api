import React from 'react';
import logo from './logo.svg';
import axios from 'axios';
import './App.css';
import CollectionList from "./CollectionList";


function App() {


  return (
    <div className="App">
      <header className="App-header">

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
        </a>
      </header>
      <div>
        <CollectionList/>
      </div>
    </div>
  );
}

export default App;
