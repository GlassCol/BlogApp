import React from 'react'
import { useApiResources } from '../../utils/useApiResources'

function PostSection() {
  const [posts, loading] = useApiResources("posts")


  return (
    <>
    {/* <div className='container'> */}
      <article className='card border border-0 w-100' >
        <dl className='card-body'>
        <dt className='card-title text-dark mb-3' > POST SECTION ... </dt>

          {loading ? <div> ... loading </div> :
              posts?.map(post => {
                return (
                  <div key={post.id} className='border-bottom mb-3' >
                    <dt><label>Title: </label> {post.title} </dt>
                    <dd><small className='border m-1 p-1' >postedBy: {post.user.username}</small></dd>
                    <dd className=''> {post.body} </dd>
                  </div>
                )
              })
          }
        </dl>
      </article>
    {/* </div> */}
    </>
  )
}

export default PostSection