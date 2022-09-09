import React, { Component } from 'react'

class CreateAnAccount extends Component {
  constructor() {
    //allows us to use setState
    super()
    this.state = {
      gretting: "welcome"
    }
  }

  render() {
    return(
      <div>
        <h1>{this.state.gretting}</h1>
      </div>
    )
  }
}
export default CreateAnAccount