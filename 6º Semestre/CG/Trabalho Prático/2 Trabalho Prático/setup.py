""" Bibliotecas externas. """
from setuptools import setup, find_packages

setup(
    name='bezier',
    version='1.0.0',
    packages=find_packages(),
    install_requires=[
        'numpy',
        'pyqt5 >= 5.11.3',
    ],
)
