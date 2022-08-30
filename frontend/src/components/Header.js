import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
function Header() {
  return (
    <>
    <Navbar bg="dark" variant="dark">
    <Container>
      <Nav className="me-auto">
        <Nav.Link href="/"> Home </Nav.Link>
        <Nav.Link href="/Contact"> Your Profile </Nav.Link>
        <Nav.Link href="/People"> Your Friends </Nav.Link>
      </Nav>
    </Container>
  </Navbar>
  </>
  )
}

export default Header