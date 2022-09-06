import { useState, useEffect } from "react";


// fetch the resource at the passed in url
export const useApiResources = (resourceUrl) => {
    const [resource, setResource] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetchResource();
        
        // testing Post 
        // postResource();

        return () => {
            // console.log('clean up here, e.g. subscribe or unsubscribe here ...')
            setLoading(true);
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [] )

    const postResource = async () => {
        return await fetch(`http://localhost:8081/posts/`, {
            method: 'POST',
            headers:  {
                'Content-Type':'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                title: 'Test', 
                body: 'Test Body',
                username: 'Ervin',
                photos: [
                    {
                        imageUrl: 'https://picsum.photos/id/330/200/300',
                        title: 'nesciunt'
                    }
                ]
            })
        });
    }


    const fetchResource = () => {
        setLoading(true)
        fetch(`http://localhost:8081/${resourceUrl}`)
            .then(response => response.json())
            .then(data => {
                if (setLoading) {
                    // console.log(`GET: ${resourceUrl}`);
                    setResource(data.data);
                    setLoading(false);
                }
            })
            .catch(error => {
                console.log(error);
                setLoading(true);
            });

    }
    return [resource, loading];
}