import React from 'react';
import axios from 'axios';



export default class CollectionList extends React.Component {
    state = {
        collections: []
    }
    componentDidMount() {
        axios.get(`http://localhost:8080/collection/cs/`)
            .then(res => {
                const collections = res.data
                console.log("Rajeev")
                console.log(collections[0])
                // console.log(collections[0].get("collectionObj"))
                this.setState({ collections })
            })
    }
  render() {
        return (
        <div>Hello Did we get the collection
            {/*{this.state.collections.forEach(collection => {collection.name})}*/}
        </div>
        )
  }
    // render() {
    //     // @ts-ignore
    //     return (
    //         <div>
    //         <ul>
    //             { this.state.collections.map(collection => <li>{collection.name}</li>)}
    //         </ul>
    //         </div>
    //     )
    // }
}