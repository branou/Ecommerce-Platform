export interface Order {
  id: string;
  date: string;
  price: number;
  status : string;
}
export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  stock: boolean;
  imageUrls : string[];
}

export interface ApiResponse<T> {
   pageNumber: number;
   pageSize: number;
   totalElements: number;
   totalPages: number;
   first: boolean;
   last: boolean;
   content: T[];

}
