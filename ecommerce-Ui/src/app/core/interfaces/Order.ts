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
  imageUrl: string;
}
