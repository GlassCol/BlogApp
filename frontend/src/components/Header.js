import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import '../css/header.css';

function Header() {
  return (
    <>
    <Navbar bg="dark" variant="dark">
    <Container>
      <Nav className="me-auto">
        <Nav.Link href="/" className='active'> Home </Nav.Link>
        <Nav.Link href='/Login'  className='active'> Sign In </Nav.Link>
        <Nav.Link href='/CreateAnAccount' className='active'> Create Account </Nav.Link>
      </Nav>
    </Container>
  </Navbar>
  </>
  )
}

export default Header