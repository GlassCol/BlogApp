import React from 'react'
import Container from 'react-bootstrap/Container'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const navigate = useNavigate();

  const handleSumbit = () =>{
    navigate('/');
  };

  return (
    <Container className="d-grid h-100">
      <Form className="text-center w-100">
        <img className="loginLogo-img" src="frontend/public/blogger.ico" alt="Login Logo" />
        <h1 className="mb-3 fs-3 fw-normal">
          Please sign in
        </h1>
        <Form.Group className="mb-1" controlId="signInUsername-id">
          <Form.Control type="text" placeholder="Username" autoComplete="username" className="position-relative" />
        </Form.Group>
        <Form.Group className="mb-1"
        controlId="signInPassword-id">
          <Form.Control type="text" placeholder="Password:" className="position-relative">
          </Form.Control>
        </Form.Group>
        <Form.Group className="d-flex justify-center-content mb-4" controlId="remberMe-btn" >
          <Form.Check label='remember me' />
        </Form.Group>
        <div className="d-grid">
          <Button variant="primary" size='lg' onClick={() => navigate("/")}>Sign in</Button>
          <Button variant='primary' size='lg' className='m-4'>Create an Account</Button>
        </div>
        <p className="mt-5">&copy; 2022-2023</p>
      </Form>
    </Container>
  )
}

export default Login