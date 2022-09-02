import React from 'react'
import { useApiResources } from '../../utils/useApiResources'
import Container from 'react-bootstrap/Container'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'

function PostCreator() {
  return (
    <Container id='post-container' className='d-grid h-100'>
        <Form id='create-post-form' className='text-center w-50'>
            <Form.Group>
            <div className='container m-2'>
                <Form.Control type='post' size='lg' placeholder='Create A Post'/>
                </div>
                <div className='container m-2'>
                <Button variant='dark' size='md' >Create Post</Button>
                </div>
            </Form.Group>
        </Form>
    </Container>
  )
}

export default PostCreator