import React from 'react'
import PostSection from '../components/postsandreplies/PostSection'
import Header from '../components/Header'
import Footer from '../components/Footer'
import { Banner } from '../components/banner/Banner'
import { SidePanel } from '../components/panels/SidePanel'
import PostCreator from '../components/postsandreplies/PostCreator'
import Login from './Login'

const PostsAndReplies = () => {
  return (
    <>
      <section className='row'>

        <Header />
          <Login />
          <section className='container'>
            <div className='row'>
              <div className='d-flex flex-wrap justify-content-between'>
                <Banner />
              </div>
              
            </div>
          </section>
          <div className='container'>
            <div className='row'>
              <article className='col col-8'>
                <PostCreator />
              </article>
            </div>
          </div>
          <div className='container'>
            <div className='row'>
                <div className='w-75 mx-auto d-block'>
                  <PostSection />
                </div>
                <div className='w-25 mx-auto d-block'
                      style={{ minWidth: '240px', maxWidth: '100vw' }}>
                  <SidePanel />
                </div>
            </div>
          </div>

        </section>
      <Footer />
    </>
        )
}

export default PostsAndReplies