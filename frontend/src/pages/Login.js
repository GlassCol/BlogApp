import React from 'react'

const Login = () => {
  return (
    <form>
        <label for="username-txt">
            <b>Username:</b>
        </label> 
        <br/>
        <input type="text" name="username-txt" id="username-txt" placeholder="Enter Username:" pattern="[A-Za-zd.]{4,8}" required></input>
        <br/>
        <label for="password-txt">
            <b>Password:</b>
        </label>
        <br/>
        <input type="text" name="password-txt" id="password-txt" placeholder="Enter Password:" required></input>
        <br/><br/>
        <button id="submit-btn" onClick="">submit</button>
    </form>
  )
}

export default Login