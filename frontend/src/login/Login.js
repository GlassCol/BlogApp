import React, {useState } from 'react'
import Container from 'react-bootstrap/Container'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import blogIcon from "../pages/blogger.png";
import { useNavigate } from 'react-router-dom';

// const [usernameReg, setUsernameReg] = useState("")
// const [passwordReg, setPasswordReg] = useState("")


const Login = () => {
  const [usernameReg, setUsernameReg] = useState("")
  const [passwordReg, setPasswordReg] = useState("")
  const navigate = useNavigate();

  const handleSumbit = () =>{
    navigate('/');
  };

  const handleCreateAnAccount = () => {
    navigate('/CreateAnAccount')
  }

  return (
    <Container className="d-grid h-100">
      <Form className="text-center w-100">
      <img className="login Logo-img p-3" 
            src={blogIcon} alt="blog-icon"
            width="200"
            height="200" />
        <h1 className="mb-3 fs-3 fw-normal">
          Please sign in
        </h1>
        <Form.Group className="mb-1"
          controlId="signInUsername-id">
          <Form.Control type="text" placeholder="Username" autoComplete="username" className="position-relative" onChange={(e) => {
            setUsernameReg(e.target.value)
          }}/>
        </Form.Group>
        <Form.Group className="mb-1"
        controlId="signInPassword-id">
          <Form.Control type="text" placeholder="Password:" className="position-relative" onChange={(e) => {
            setPasswordReg(e.target.value)
          }}/>
        </Form.Group>
        <div className="d-grid">
          <Button variant="primary" size='lg' onClick={() => navigate("/")}>Sign in</Button>
          <Button variant='primary' size='lg' className='m-4' onClick={handleCreateAnAccount}>Create an Account</Button>
        </div>
        {/* <p className="mt-5">&copy; 2022-2023</p> */}
      </Form>
    </Container>
  )
}

export default Login