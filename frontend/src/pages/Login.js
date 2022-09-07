import React from 'react'
import Container from 'react-bootstrap/Container'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import blogIcon from "./blogger.png";
import { useNavigate } from 'react-router-dom';

const [usernameReg, setUsernameReg] = useState("")
const [passwordReg, setPasswordReg] = useState("")


const Login = () => {
  const navigate = useNavigate();

  const handleSumbit = () =>{
    navigate('/');
  };

  return (
<<<<<<< HEAD:frontend/src/pages/Login.js
    <Container className="d-grid h-100 ">
      <Form className="text-center w-100 ps-5 pe-5 mb-5">
        <img className="login Logo-img p-3" 
            src={blogIcon} 
            width="80" 
            height="90" 
            alt="Login Logo" />
        <h1 className="mb-3 fs-3 fw-normal">
          Please sign in
        </h1>
        <Form.Group className="mb-1" controlId="signInUsername-id">
          <Form.Control type="text" placeholder="Username" autoComplete="username" className="position-relative" />
=======
    <Container className="d-grid h-100">
      <Form className="text-center w-100">
        <h1 className="mb-3 fs-3 fw-normal">
          Please sign in
        </h1>
        <Form.Group className="mb-1"
          controlId="signInUsername-id">
          <Form.Control type="text" placeholder="Username" autoComplete="username" className="position-relative" onChange={(e) => {
            setUsernameReg(e.target.value)
          }}/>
>>>>>>> 21a2c00a0318e5e79df0178a813e9ad2509d61e6:frontend/src/components/panels/login/Login.js
        </Form.Group>
        <Form.Group className="mb-1"
        controlId="signInPassword-id">
          <Form.Control type="text" placeholder="Password:" className="position-relative" onChange={(e) => {
            setPasswordReg(e.target.value)
          }}/>
        </Form.Group>
        <div className="d-grid">
          <Button variant="primary" size='lg' onClick={() => navigate("/")}>Sign in</Button>
          <Button variant='primary' size='lg' className='m-4'>Create an Account</Button>
        </div>
        {/* <p className="mt-5">&copy; 2022-2023</p> */}
      </Form>
    </Container>
  )
}

export default Login