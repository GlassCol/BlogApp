import { useApiResources } from "../../utils/useApiResources"

export const Banner = () => {
    const [photos, loading] = useApiResources("posts/photos")
    // console.log(photos);

    return (
        <>
            {loading ? <div> ... loading </div> :
                photos.map(photo => {
                    return (
                    <img key={photo.id} 
                        className="mx-auto d-block rounded shadow bg-body p-3 m-3" 
                        src={photo.imageUrl} 
                        alt={photo.title}
                        width="200"
                        height="200"   />        
                    )         
                })
                
            }
        </>
    )
}