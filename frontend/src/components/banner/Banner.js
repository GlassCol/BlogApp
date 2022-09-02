import { useApiResources } from "../../utils/useApiResources"

export const Banner = () => {
    const [photos, loading] = useApiResources("posts/photos")

    return (
        <>
            <div className="row m-1">
                <div className="border-0 d-flex justify-content-between p-3 m-auto">

                    {loading ? <div> ... loading </div> :
                        photos.map(photo => {
                            return (
                            <img key={photo.id} className="bd-placeholder-img rounded" 
                                src={photo.imageUrl} 
                                alt={photo.title}
                                width="200"
                                height="200"   />        
                            )         
                        })
                     
                    }
                </div>
            </div>
        </>
    )
}