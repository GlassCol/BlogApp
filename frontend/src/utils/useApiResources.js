import { useState, useEffect } from "react";


// fetch the resource at the passed in url
export const useApiResources = (resourceUrl) => {
    const [resource, setResource] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetchResource();

        return () => {
            // console.log('clean up here, e.g. subscribe or unsubscribe here ...')
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [] )


    const fetchResource = () => {
        setLoading(true);
        fetch(`http://localhost:8081/${resourceUrl}`)
            .then(response => response.json())
            .then(data => {
                console.log(`GET: ${resourceUrl}`);
                setResource(data.data);
                setLoading(false);
            })
            .catch(error => {
                console.log(error);
                setLoading(false);
            });
    }
    return [resource, loading];
}