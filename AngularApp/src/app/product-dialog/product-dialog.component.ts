import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Product } from 'src/models/Product';

@Component({
  selector: 'app-product-dialog',
  templateUrl: './product-dialog.component.html',
  styleUrls: ['./product-dialog.component.css']
})
export class ProductDialogComponent {
   product : Product;
   errorMessage: string = '';

   constructor(
    public dialogRef: MatDialogRef<ProductDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Product
  ) {
    this.product = { ...data };
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

  onSaveClick(): void {
    if (this.validateForm()) {
      console.log('0'  + this.product.sortType)
      this.dialogRef.close(this.product);
    }
  }

  validateForm(): boolean {
    if (!this.product.name) {
      this.errorMessage = 'Name is required';
      return false;
    }
    if(!this.product.description) {
      this.errorMessage = 'Description is required';
      return false;
    }
    if(!this.product.price) {
      this.errorMessage = 'Price is required';
      return false;
    }
    if(!this.product.rating) {
      this.errorMessage = 'Rating is required';
      return false;
    }
    if(this.product.rating > 5 || this.product.rating < 0) {
      this.errorMessage = 'Rating must be between 0 and 5';
      return false;
    }
    if(!this.product.image) {
      this.errorMessage = 'Image is required';
      return false;
    }
    
    return true;
  }

}
